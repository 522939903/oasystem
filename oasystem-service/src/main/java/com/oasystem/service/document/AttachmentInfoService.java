package com.oasystem.service.document;

import com.oasystem.ResultDTO;
import com.oasystem.model.AttachmentInfo;

import java.util.List;


public interface AttachmentInfoService {
    /**
     * 查询所有附件
     *
     * @return 附件信息
     */
    ResultDTO<List<AttachmentInfo>> queryAllAttachmentInfo(Integer fileId);

    /**
     * 新增附件
     *
     * @param attachmentInfo
     * @return
     */
    ResultDTO<Integer> addAttachment(AttachmentInfo attachmentInfo);

    /**
     * 删除附件
     *
     * @param attachmentId
     * @return
     */
    ResultDTO<Integer> deleteAttachment(Integer attachmentId, Integer isDelete);
}
