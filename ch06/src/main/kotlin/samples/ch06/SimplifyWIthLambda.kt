package samples.ch06

fun bindData(bean: ContentBean) {
    val titleTV  = findViewById<TextView>(R.id.iv_title)
    val contentTV  = findViewById<TextView>(R.id.iv_content)

    with(bean) {
        titleTV.text = this.title // this可以省略
        titleTV.textSize = this.titleFontSize
        contentTV.text = this.content
        contentTV.text = this.contentFontSize
    }
}

fun bindData1(bean: ContentBean) {
    val titleTV  = findViewById<TextView>(R.id.iv_title)
    val contentTV  = findViewById<TextView>(R.id.iv_content)

    bean.apply {
        titleTV.text = this.title // this可以省略
        titleTV.textSize = this.titleFontSize
        contentTV.text = this.content
        contentTV.text = this.contentFontSize
    }
}