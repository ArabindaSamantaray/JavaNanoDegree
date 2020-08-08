package com.udacity.jwdnd.course1.cloudstorage.Models;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.Arrays;



public class Files {
    public Integer fileId;
    public String filename;
    public String contenttype;
    public long filesize;

    public Integer userid;

    @Lob
    @Column(name = "filedata", columnDefinition="BLOB")
    private byte[] filedata;

    public Files(Integer fileId, String fileName, String contenttype, long filesize, Integer userid, byte[] filedata) {
        this.fileId = fileId;
        this.filename = fileName;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    @Override
    public String toString() {
        return "Files{" + "fileId=" + fileId + ", filename='" + filename + '\'' + ", contenttype='" + contenttype + '\''
            + ", filesize=" + filesize + ", userid=" + userid + ", filedata=" + Arrays.toString(filedata) + '}';
    }
}
