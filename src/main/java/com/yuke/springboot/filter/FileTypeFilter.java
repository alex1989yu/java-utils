package com.yuke.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;

/**
 * @author yuke
 * @version 1.0
 */
@Slf4j
public class FileTypeFilter implements FileFilter {

    private final String[] fileTypes;

    public FileTypeFilter(final String fileType) {
        this.fileTypes = new String[]{fileType};
    }

    public FileTypeFilter(final String[] fileTypes) {
        this.fileTypes = new String[fileTypes.length];
        System.arraycopy(fileTypes, 0, this.fileTypes, 0, fileTypes.length);
    }

    @Override
    public boolean accept(File pathname) {
        final String name = pathname.getName();
        for (final String suffix : this.fileTypes) {
            if (check(name, suffix)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(final String str, final String end) {
        final int endLen = end.length();
        return str.regionMatches(true, str.length() - endLen, end, 0, endLen);
    }
}
