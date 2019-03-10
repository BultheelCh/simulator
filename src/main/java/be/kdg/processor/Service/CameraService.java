package be.kdg.processor.Service;

import org.springframework.stereotype.Component;

@Component
//@Cacheable
public interface CameraService {
    public String get(int cameraId);
}
