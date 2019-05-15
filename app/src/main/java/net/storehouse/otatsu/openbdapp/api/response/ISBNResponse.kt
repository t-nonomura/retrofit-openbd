package net.storehouse.otatsu.openbdapp.api.response

data class ISBNResponse(
    var summary: Summary? = null
)

data class Summary(
    /** 書籍名 */
    var title: String? = null,
    /** 出版社 */
    var publisher: String? = null,
    /** 巻 */
    var volume: String? = null,
    /** シリーズ */
    var series: String? = null,
    /** 著者 */
    var author: String? = null,
    /** 出版日 */
    var pubdate: String? = null,
    /** サムネイルURI */
    var cover: String? = null,
    /** 詳細 */
    var description: String? = null,
    /** ISBN */
    var isbn: String? = null
)