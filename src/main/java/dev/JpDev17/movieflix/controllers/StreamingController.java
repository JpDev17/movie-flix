package dev.JpDev17.movieflix.controllers;

import dev.JpDev17.movieflix.request.StreamingRequest;
import dev.JpDev17.movieflix.response.StreamingResponse;
import dev.JpDev17.movieflix.services.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        List<StreamingResponse> streamingList = streamingService.getAll();
        return ResponseEntity.ok(streamingList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id) {
        StreamingResponse streaming = streamingService.getById(id);
        return ResponseEntity.ok(streaming);
    }

    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
        StreamingResponse newStreaming = streamingService.createStreaming(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStreaming);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StreamingResponse> updateByStreamingId(@PathVariable Long id, @RequestBody StreamingRequest request) {
        StreamingResponse updatedStreaming = streamingService.updateStreaming(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedStreaming);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByStreamingId(@PathVariable Long id) {
        streamingService.deleteById(id);
        return ResponseEntity.ok("Category with id " + id + " deleted successfully");
    }
}
