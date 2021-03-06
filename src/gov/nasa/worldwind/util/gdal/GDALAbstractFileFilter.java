/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package gov.nasa.worldwind.util.gdal;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

import gov.nasa.worldwind.util.*;

/**
 * @author Lado Garakanidze
 * @version $Id: GDALAbstractFileFilter.java 1171 2013-02-11 21:45:02Z dcollins $
 */
abstract class GDALAbstractFileFilter implements java.io.FileFilter {

    protected HashSet<String> listFolders = new HashSet<>();
    protected final String searchPattern;

    protected GDALAbstractFileFilter(String searchPattern) {
        if (null == searchPattern || searchPattern.length() == 0) {
            String message = Logging.getMessage("nullValue.StringIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        this.searchPattern = searchPattern;

        listFolders.clear();
    }

    protected boolean isHidden(String path) {
        if (!WWUtil.isEmpty(path)) {
            String[] folders = path.split(Pattern.quote(File.separator));
            if (!WWUtil.isEmpty(folders)) {
                for (String folder : folders) {
                    if (!WWUtil.isEmpty(folder) && folder.startsWith(".")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<String> getFolders() {
        return new ArrayList<>(listFolders);
    }
}
