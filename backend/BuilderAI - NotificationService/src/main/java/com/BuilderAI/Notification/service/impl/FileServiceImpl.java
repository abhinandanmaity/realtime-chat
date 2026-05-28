package com.BuilderAI.Notification.service.impl;


import com.project.BuilderAI.dto.project.FileContentResponse;
import com.project.BuilderAI.dto.project.FileNode;
import com.project.BuilderAI.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
