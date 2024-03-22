package com.metlife.pdf.service

import com.aallam.openai.api.embedding.EmbeddingRequest
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.time.Duration.Companion.seconds

@Service
class PdfProcessingService(
    private val searchRepository: SearchRepository,
    @Value("\${openai.api.key}") apiKey: String
) {
    private val openAI = OpenAI(token = apiKey, timeout = Timeout(socket = 120.seconds))

    suspend fun processPdfFiles(files: List<MultipartFile>) = coroutineScope {
        files.map { file ->
            async(Dispatchers.IO) {
                // Convert PDF to text (implement according to your needs)
                val textContent = convertPdfToText(file.bytes)

                // Generate embeddings using OpenAI API
                val embeddings = openAI.embeddings(
                    EmbeddingRequest(
                        model = ModelId("text-ada-001"),
                        input = listOf(textContent)
                    )
                ).toList() // Assuming you have an asynchronous stream

                // Store embeddings in Elastic Search
                searchRepository.saveEmbeddings(embeddings)
            }
        }.forEach { it.await() } // Wait for all the async operations to complete
    }
}