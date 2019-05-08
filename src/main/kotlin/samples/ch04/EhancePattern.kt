package samples.ch04

object expr1 {
	sealed class Expr {
		abstract fun isZero(): Boolean
		abstract fun isAddZero(): Boolean
		data class Num(val value: Int) : Expr() {
			override fun isZero(): Boolean = this.value == 0
			override fun isAddZero(): Boolean = false
		}

		data class Operate(val opName: String, val left: Expr, val right: Expr) : Expr() {
			override fun isZero(): Boolean = false
			override fun isAddZero(): Boolean = this.opName == "+" && (this.left.isZero() || this.right.isZero())
		}
	}
}

object expr2 {
	sealed class Expr {
		abstract fun isZero(): Boolean
		abstract fun isAddZero(): Boolean
		abstract fun left(): Expr
		abstract fun right(): Expr
		data class Num(val value: Int) : Expr() {
			override fun isZero(): Boolean = this.value == 0
			override fun isAddZero(): Boolean = false
			override fun left(): Expr = throw Throwable("no element")
			override fun right(): Expr = throw Throwable("no element")
		}

		data class Operate(val opName: String, val left: Expr, val right: Expr) : Expr() {
			override fun isZero(): Boolean = false
			override fun isAddZero(): Boolean = this.opName == "+" && (this.left.isZero() || this.right.isZero())
			override fun left(): Expr = this.left
			override fun right(): Expr = this.right
		}
	}

	fun simplifyExpr(expr: Expr): Expr = when {
		expr.isAddZero() && expr.right().isAddZero() && expr.right().left().isZero() -> expr.right().right()
		else -> expr
	}
}

object expr3 {
	sealed class Expr {
		abstract fun accept(v: Visitor): Boolean
		class Num(val value: Int) : Expr() {
			override fun accept(v: Visitor): Boolean = v.visit(this)
		}

		class Operate(val opName: String, val left: Expr, val right: Expr) : Expr() {
			override fun accept(v: Visitor): Boolean = v.visit(this)
		}
	}

	class Visitor {
		fun visit(expr: Expr.Num): Boolean = false
		fun visit(expr: Expr.Operate): Boolean = when (expr) {
			Expr.Operate("+", Expr.Num(0), expr.right) -> true
			Expr.Operate("+", expr.left, Expr.Num(0)) -> true
			else -> false
		}
	}
}

object expr4 {
	sealed class Expr {
		abstract fun isZero(v: Visitor): Boolean
		abstract fun isAddZero(v: Visitor): Boolean
		abstract fun simplifyExpr(v: Visitor): Expr

		class Num(val value: Int) : Expr() {
			override fun isZero(v: Visitor): Boolean = v.matchZero(this)
			override fun isAddZero(v: Visitor): Boolean = v.matchAddZero(this)
			override fun simplifyExpr(v: Visitor): Expr =
					v.doSimplifyExpr(this)
		}

		class Operate(val opName: String, val left: Expr, val right: Expr) : Expr() {
			override fun isZero(v: Visitor): Boolean = v.matchZero(this)

			override fun isAddZero(v: Visitor): Boolean = v.matchAddZero(this)
			override fun simplifyExpr(v: Visitor): Expr = this
		}
	}

	class Visitor {
		fun matchAddZero(expr: Expr.Num): Boolean = false
		fun matchAddZero(expr: Expr.Operate): Boolean = when (expr) {
			Expr.Operate("+", Expr.Num(0), expr.right)
			-> true
			Expr.Operate("+", expr.left,
					Expr.Num(0))
			-> true
			else -> false
		}

		fun matchZero(expr: Expr.Num): Boolean = expr.value == 0
		fun matchZero(expr: Expr.Operate): Boolean = false
		fun doSimplifyExpr(expr: Expr.Num): Expr = expr
		fun doSimplifyExpr(expr: Expr.Operate, v: Visitor): Expr = when {
			(expr.right is Expr.Num && v.matchAddZero(expr) && v.matchAddZero(expr.right)) && (expr.right
					is Expr.Operate && expr.right.left is Expr.Num) && v.matchZero(expr.right.left) -> expr.right.left
			else -> expr
		}
	}
}