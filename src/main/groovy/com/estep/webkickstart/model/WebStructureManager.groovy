package com.estep.webkickstart.model

import com.estep.webkickstart.ProjectHelper

/**
 * Handles the creation of the project and module folder structures for the web UI and services project.
 */
class WebStructureManager {
    WebStructureManager() {
    }

    /**
     * Creates the web structure on the file system.
     */
    void createWebStructure() {
        ProjectHelper.createConfigStructure()

        createWebModuleSourceStructure("main")
        createWebModuleSourceStructure("test")

        createWebContentSourceStructure("app")
        createWebContentSourceStructure("images")
        createWebContentSourceStructure("WEB-INF")
    }

    /**
     * Deletes the web structure from the file system.
     */
    void deleteWebStructure() {
    }

    /**
     * Creates the web java source structure.
     * @param folderName the folder name to place the structure in.
     */
    protected void createWebModuleSourceStructure(folderName) {
        def srcPath = "src" + File.separator + folderName
        def path = ProjectHelper.createSourceStructure("web.base.path", srcPath)
        ProjectHelper.createCodeStructure("web.security.path", path)
        ProjectHelper.createCodeStructure("web.user.path", path)
    }

    /**
     * Creates the web content structure.
     * @param folderName the folder name to place the structure in.
     */
    protected void createWebContentSourceStructure(folderName) {
        def template = Property.get("web.base.path") + File.separator + "WebContent" + File.separator + folderName
        def path = TextTemplate.renderDeep(template)
        ProjectHelper.makeDirectories(path)
    }
}