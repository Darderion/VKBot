package com.gogo.steelbotrun.vkbot.sdk

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.impl.client.HttpClientBuilder
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URL

class HttpClient {
	companion object {
		fun sendMultiPartFormRequest(uploadUrl: String, filePath: String): String {
			val builder = HttpClientBuilder.create()
			val httpClient = builder.build()
			val httpPost = HttpPost(uploadUrl)
			val entityBuilder = MultipartEntityBuilder.create()
			entityBuilder.addPart("file", FileBody(File(filePath)))
			val entity = entityBuilder.build()
			httpPost.entity = entity
			val response = httpClient.execute(httpPost)

			val reader = InputStreamReader(response.entity.content)
			val bufferedReader = BufferedReader(reader)
			var inputLine: String? = bufferedReader.readLine()
			val response2 = StringBuffer()
			while (inputLine != null) {
				response2.append(inputLine)
				inputLine = bufferedReader.readLine()
			}
			reader.close()
			return response2.toString()
		}

		fun sendRequest(
			url: String,
			requestType: SDK.RequestType = SDK.RequestType.Get
		): String {
			var url = url
				.replace(" ", "%20")
				.replace("'", "%60")
				.replace("\n", "%0A")

			val urlObject = URL(url)
			val conn = urlObject.openConnection()

			if (requestType == SDK.RequestType.Post) {
				conn.doOutput = true
			}

			val stream = conn.getInputStream()
			val streamReader = InputStreamReader(stream)
			val response = BufferedReader(streamReader)
			val output = response.readLine()
			response.close()
			return output
		}
	}
}