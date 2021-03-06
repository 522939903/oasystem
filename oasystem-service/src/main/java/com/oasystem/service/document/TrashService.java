package com.oasystem.service.document;

import com.oasystem.ResultDTO;


public interface TrashService {

    /**
     * 还原附件
     *
     * @param attachmentId
     * @return
     */
    ResultDTO<Integer> returnAttachment(Integer attachmentId, Integer isDelete);

    /**
     * 还原文件
     *
     * @param fileId
     * @param isDelete
     * @return
     */
    ResultDTO<Integer> returnFile(Integer fileId, Integer isDelete);

    /**
     * 彻底删除文档
     *
     * @param fileId
     * @return
     */
    ResultDTO<Integer> trashFile(Integer fileId);

    /**
     * 彻底删除附件
     *
     * @param attachmentId
     * @return
     */
    ResultDTO<Integer> trashAttachment(Integer attachmentId);


}
