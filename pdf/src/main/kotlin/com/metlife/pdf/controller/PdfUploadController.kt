package com.metlife.pdf.controller

import com.metlife.pdf.service.PdfProcessingService
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Mono

@RestController
class PdfUploadController(
    private val pdfProcessingService: PdfProcessingService
) {

    @PostMapping("/upload")
    fun uploadFiles(
        @RequestParam("files") files: Array<MultipartFile>
    ): Mono<ResponseEntity<String>> {
        return mono {
            pdfProcessingService.processPdfFiles(files.toList())
            "Files are being processed"
        }.map { ResponseEntity(it, HttpStatus.ACCEPTED) }
    }
}