package com.estep.bigcodebang

import com.estep.bigcodebang.model.ServerProperty
import com.estep.bigcodebang.model.TextTemplate

/**
 * Contains static helper methods for project creation purposes.
 *
 * @author dougestep.
 */
class ProjectHelper {

    /**
     * Creates the config folder structures.
     */
    static void createConfigStructure() {
        def basePath = TextTemplate.renderDeep(ServerProperty.get("root.base.path"))
        makeDirectories(basePath + "/config/checkstyle")
        makeDirectories(basePath + "/config/rulesets")
    }

    /**
     * Creates the directory structure supplied in the path.
     *
     * @param path the path.
     */
    static void makeDirectories(String path) {
        File file = new File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
    }

    /**
     * Creates the folder structure structure related to the supplied source property/information.
     *
     * @param basePathProperty the property pointing to the base folder structure.
     * @param path the path to append to the base structure.
     * @return the created path.
     */
    static void createCodeStructure(basePathProperty, path) {
        def value = TextTemplate.renderDeep(ServerProperty.get(basePathProperty))
        File file = new File(path + '/' + value)
        file.mkdirs()
    }

    /**
     * Creates the source structure (java, resource folders) related to the supplied source property/information.
     *
     * @param basePathProperty the property pointing to the base folder structure.
     * @param folderName the folder name to append to the base structure.
     * @return the created path.
     */
    static String createSourceStructure(basePathProperty, folderName) {
        def template = ServerProperty.get(basePathProperty) + '/' + folderName
        def path = TextTemplate.renderDeep(template)

        def resources = path + "/resources"
        ProjectHelper.makeDirectories(resources)

        path += "/java"
        ProjectHelper.makeDirectories(path)

        path
    }
}
