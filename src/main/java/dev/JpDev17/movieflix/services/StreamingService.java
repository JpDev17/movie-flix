package dev.JpDev17.movieflix.services;

import dev.JpDev17.movieflix.entities.Streaming;
import dev.JpDev17.movieflix.mapper.StreamingMapper;
import dev.JpDev17.movieflix.repositories.StreamingRepository;
import dev.JpDev17.movieflix.request.StreamingRequest;
import dev.JpDev17.movieflix.response.StreamingResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository) {
        this.streamingRepository = streamingRepository;
    }

    public List<StreamingResponse> getAll() {
        List<Streaming> streamingList = streamingRepository.findAll();
        return streamingList.stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    public StreamingResponse getById(Long id) {
        Streaming streaming = streamingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Streaming with id " + id + " not found!"));
        return StreamingMapper.toStreamingResponse(streaming);
    }

    public StreamingResponse createStreaming(StreamingRequest request) {
        Streaming createdStreaming = StreamingMapper.toStreaming(request);
        Streaming newStreaming = streamingRepository.save(createdStreaming);
        return StreamingMapper.toStreamingResponse(newStreaming);
    }

    public StreamingResponse updateStreaming(Long id, StreamingRequest request) {
        if (!streamingRepository.existsById(id)) {
            throw new EntityNotFoundException("Streaming with id " + id + " not found!");
        }
        Streaming createdStreaming = StreamingMapper.toStreaming(request);
        createdStreaming.setId(id);
        Streaming newStreaming = streamingRepository.save(createdStreaming);
        return StreamingMapper.toStreamingResponse(newStreaming);
    }

    public void deleteById(Long id) {
        if (!streamingRepository.existsById(id)) {
            throw new EntityNotFoundException("Streaming with id " + id + " not found!");
        }
       streamingRepository.deleteById(id);
    }
}
