package hr.lknezevic.taskflow.taskflowgui.utils;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.dd.plist.XMLPropertyListWriter;
import hr.lknezevic.taskflow.taskflowgui.enums.PlistProperty;

import java.io.File;

public class PlistUtil {
    private final File plistFile;

    public PlistUtil() {
        this.plistFile = new File(System.getProperty("user.dir"), "config.plist");
        createPlistIfNotExists();
    }

    private void createPlistIfNotExists() {
        if (plistFile.exists()) return;
        if (!plistFile.getParentFile().exists()) {
            plistFile.getParentFile().mkdirs();
        }
        try {
            XMLPropertyListWriter.write(getDefaultPlist(), plistFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create plist", e);
        }
    }

    public String readValue(PlistProperty plistProperty) {
        try {
            NSDictionary root = (NSDictionary) PropertyListParser.parse(plistFile);
            return root.get(plistProperty.getKey()).toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read plist", e);
        }
    }

    public void writeValue(PlistProperty key, String value) {
        try {
            NSDictionary root = (NSDictionary) PropertyListParser.parse(plistFile);
            root.put(key.getKey(), value);
            XMLPropertyListWriter.write(root, plistFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write plist", e);
        }
    }

    public void resetToDefault() {
        try {
            NSDictionary root = (NSDictionary) PropertyListParser.parse(plistFile);
            root.put(PlistProperty.LANGUAGE.getKey(), "en");
            root.put(PlistProperty.THEME.getKey(), "light");
            root.put(PlistProperty.VERSION.getKey(),  "1.0.0");
            XMLPropertyListWriter.write(root, plistFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create plist", e);
        }
    }

    private NSDictionary getDefaultPlist() {
        NSDictionary root = new NSDictionary();
        root.put(PlistProperty.LANGUAGE.getKey(), "en");
        root.put(PlistProperty.THEME.getKey(), "light");
        root.put(PlistProperty.ACCESS_TOKEN.getKey(), "");
        root.put(PlistProperty.VERSION.getKey(), "1.0.0");
        return root;
    }
}
