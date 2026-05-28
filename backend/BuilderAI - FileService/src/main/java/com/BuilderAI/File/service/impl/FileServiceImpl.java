package com.BuilderAI.File.service.impl;

import com.BuilderAI.File.dto.project.FileNode;
import com.BuilderAI.File.dto.project.Response.FileContentResponse;
import com.BuilderAI.File.service.FileService;
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
