package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ProcessingStatusService {
    private final ConcurrentHashMap<String, String> statusMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, HashMap<String, String>> fileDataMap = new ConcurrentHashMap<>();

    public void setStatus(String requestId, String status) {
        statusMap.put(requestId, status);
    }

    public String getStatus(String requestId) {
        return statusMap.getOrDefault(requestId, "Not Found");
    }

    public void removeStatus(String requestId) {
        statusMap.remove(requestId);
        fileDataMap.remove(requestId); // Also clean up file data
    }

    // New Methods for storing and retrieving file data
    public void setFileData(String requestId, HashMap<String, String> response) {
        fileDataMap.put(requestId, response);
    }

    public HashMap<String, String> getFileData(String requestId) {
        return fileDataMap.get(requestId);
    }
}


