/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kitravee
 */
//class to all path
public class FileSearch {

    private String fileNameToSearch;
    private List<String> result = new ArrayList<String>();

    public String getFileNameToSearch() {
        return fileNameToSearch;
    }

    public void setFileNameToSearch(String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    public List<String> getResult() {
        return result;
    }

    public void searchDirectory(File directory) {
        setFileNameToSearch(fileNameToSearch);

        if (directory.isDirectory()) {
            search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " : is not a directory!");
        }

    }

    private void search(File file) {

        if (file.isDirectory()) {

            try {
                if (file.canRead()) {
                    for (File temp : file.listFiles()) {
                        if (temp.isDirectory()) {
                            search(temp);

                        } else {
                            result.add(temp.getAbsoluteFile().toString());
                        }
                    }

                } else {
                    System.out.println(file.getAbsoluteFile().toString() + "Permission Denied");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
