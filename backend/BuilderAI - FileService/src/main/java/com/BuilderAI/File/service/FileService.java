package com.BuilderAI.File.service;


import com.BuilderAI.File.dto.project.FileNode;
import com.BuilderAI.File.dto.project.Response.FileContentResponse;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
