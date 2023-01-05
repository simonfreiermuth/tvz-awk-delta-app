package ch.tvzeiningen.awkdeltaapp.data

import ch.tvzeiningen.awkdeltaapp.model.Training
import ch.tvzeiningen.awkdeltaapp.parse
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.time.LocalDate

class XoyondoConnector : TrainingDataConnector {
    private val URL = "https://xoyondo.com/export?ID=SEOJOuH7ncrBkng&a=TLONfkKGj4&f=csv"

    override fun getTrainings(): List<Training> = request()

    override fun getTraining(date: LocalDate): Training? =
        request().firstOrNull { t -> t.date == date }

    private fun request(): List<Training> {
        /** implemented with OKHttpClient because otherwise the download from xoyondos
         * cloudflaire server doesn't work (challange response)
         */
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(URL)
            .get()
            .addHeader("accept-language", "en")
            .addHeader("cach", "no-cache")
            .addHeader("cookie", "lang=en-uk;") // important to receive csv in english
            .build()

        val response = client.newCall(request).execute()

        if (response.code != 200 || response.body == null) throw IOException()

        return parse(response.body!!.byteStream())
    }
}