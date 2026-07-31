package dev.JpDev17.movieflix.mapper;

import dev.JpDev17.movieflix.entities.Streaming;
import dev.JpDev17.movieflix.request.StreamingRequest;
import dev.JpDev17.movieflix.response.StreamingResponse;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        Streaming streaming = new Streaming();
                streaming.setName(streamingRequest.name());
                return streaming;
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return new StreamingResponse(
                streaming.getId(),
                streaming.getName()
        );
    }
}
