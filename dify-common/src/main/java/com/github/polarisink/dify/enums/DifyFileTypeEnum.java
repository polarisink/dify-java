package com.github.polarisink.dify.enums;

/**
 * 文件类型枚举
 */
public enum DifyFileTypeEnum {

    /**
     * 具体类型包含：'TXT', 'MD', 'MARKDOWN', 'PDF', 'HTML', 'XLSX', 'XLS', 'DOCX', 'CSV', 'EML', 'MSG', 'PPTX', 'PPT', 'XML', 'EPUB'
     */
    document,
    /**
     * 具体类型包含：'JPG', 'JPEG', 'PNG', 'GIF', 'WEBP', 'SVG'
     */
    image,
    /**
     * 具体类型包含：'MP3', 'M4A', 'WAV', 'WEBM', 'AMR'
     */
    audio,
    /**
     * 具体类型包含：'MP4', 'MOV', 'MPEG', 'MPGA'
     */
    video,
    /**
     * 具体类型包含：其他文件类型
     */
    custom
}
