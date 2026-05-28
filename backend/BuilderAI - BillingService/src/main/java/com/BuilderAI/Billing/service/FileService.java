package com.BuilderAI.Billing.service;

import com.project.BuilderAI.dto.project.FileContentResponse;
import com.project.BuilderAI.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
