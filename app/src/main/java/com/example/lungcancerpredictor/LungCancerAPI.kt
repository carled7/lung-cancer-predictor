import android.os.AsyncTask
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class HttpPostTask(private val callback: (String) -> Unit) : AsyncTask<Void, Void, String>() {

    override fun doInBackground(vararg params: Void?): String {
        val url = URL(" https://3428-179-106-16-192.ngrok-free.app/predict")
        val connection = url.openConnection() as HttpURLConnection
        val data = "{\"data\": [\"M\", 47, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1]}"
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json; utf-8")
        connection.setRequestProperty("Accept", "application/json")
        connection.doOutput = true
        try {
            val outputStream = connection.outputStream
            outputStream.write(data.toByteArray(Charsets.UTF_8))
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val responseCode = connection.responseCode
        return if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = BufferedReader(InputStreamReader(connection.inputStream)).use { it.readText() }
            response
        } else {
            "POST request failed with response code $responseCode"
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null) {
            callback(result)
        }
    }
}
