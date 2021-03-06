package com.estep.bigcodebang.model

import com.estep.bigcodebang.ProjectHelper

/**
 * Handles the creation of the project and module folder structures for the Model projects.
 *
 * @author dougestep.
 */
class ServerModelStructureManager {
    ServerModelStructureManager() {
    }

    /**
     * Creates the model structure on the file system.
     */
    void createModelStructure() {
        def basePath = TextTemplate.renderDeep(ServerProperty.get("root.base.path"))
        ProjectHelper.makeDirectories(basePath + "/gradle/wrapper")

        def modelPath = TextTemplate.renderDeep(ServerProperty.get("model.base.path"))
        ProjectHelper.makeDirectories(modelPath + "/gradle/wrapper")

        ProjectHelper.createConfigStructure()
        createAppLogicModuleStructure("main")
        createAppLogicModuleStructure("test")

        createDataModuleStructure("main")
        createDataModuleStructure("test")

        createSharedModuleStructure("main")
        createSharedModuleStructure("test")
    }

    /**
     * Deletes the model structure from the file system.
     */
    void deleteModelStructure() {
        def modelBasePath = TextTemplate.renderDeep(ServerProperty.get("model.base.path"))

        File file = new File(modelBasePath)
        file.deleteDir()
    }

    /**
     * Creates the application logic module structure.
     * @param folderName the folder name to place the structure in.
     */
    protected void createAppLogicModuleStructure(folderName) {
        def path = ProjectHelper.createSourceStructure("applogic.base.path", folderName)

        ProjectHelper.createCodeStructure("applogic.aspect", path)
        ProjectHelper.createCodeStructure("applogic.service.lookup", path)
        ProjectHelper.createCodeStructure("applogic.service.security", path)
        ProjectHelper.createCodeStructure("applogic.service.user", path)
    }

    /**
     * Creates the data module structure.
     * @param folderName the folder name to place the structure in.
     */
    protected void createDataModuleStructure(folderName) {
        def path = ProjectHelper.createSourceStructure("data.base.path", folderName)
        ProjectHelper.createCodeStructure("model.data.lookup", path)
        ProjectHelper.createCodeStructure("model.data.mail", path)
        ProjectHelper.createCodeStructure("model.data.user", path)
    }

    /**
     * Creates the shared module structure.
     * @param folderName the folder name to place the structure in.
     */
    protected createSharedModuleStructure(folderName) {
        def path = ProjectHelper.createSourceStructure("shared.base.path", folderName)
        ProjectHelper.createCodeStructure("model.shared.exception", path)
        ProjectHelper.createCodeStructure("model.shared.criteria", path)
        ProjectHelper.createCodeStructure("model.shared.data", path)
        ProjectHelper.createCodeStructure("model.shared.message", path)
        ProjectHelper.createCodeStructure("model.shared.log", path)
        ProjectHelper.createCodeStructure("model.shared.util", path)
    }
}
