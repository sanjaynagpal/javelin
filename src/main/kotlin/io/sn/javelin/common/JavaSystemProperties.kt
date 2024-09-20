package io.sn.io.sn.javelin.common

import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.AWT_AA_FONT_SETTINGS
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.AWT_HEADLESS
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_CLASS_PATH
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_CLASS_VERSION
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_HOME
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_IO_TMPDIR
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_SPEC_NAME
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_SPEC_VENDOR
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_SPEC_VERSION
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_VENDOR
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_VENDOR_URL
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.JAVA_VERSION
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.OS_NAME
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.OS_VERSION
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.USER_DIR
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.USER_LANGUAGE
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.USER_NAME
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.VM_NAME
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.VM_SPEC_NAME
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.VM_SPEC_VENDOR
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.VM_VENDOR
import io.sn.io.sn.javelin.common.JavaSystemPropertiesConstant.VM_VERSION
import java.io.File
import java.nio.file.FileSystems

object JavaSystemProperties {

    fun getJavaVersion(): String = System.getProperty(JAVA_VERSION)

    fun getJavaVendor() : String = System.getProperty(JAVA_VENDOR)

    fun getJavaVendorUrl() : String = System.getProperty(JAVA_VENDOR_URL)

    fun getJavaClassVersion() : String = System.getProperty(JAVA_CLASS_VERSION)

    fun getJavaClassPath() : String = System.getProperty(JAVA_CLASS_PATH)

    fun getOsName() : String = System.getProperty(OS_NAME)

    fun getOsVersion() : String = System.getProperty(OS_VERSION)

    fun getFileSeparator() : String = FileSystems.getDefault().separator

    fun getPathSeparator() : String = File.pathSeparator

    fun getLineSeparator() : String = System.lineSeparator()

    fun getJavaSpecVersion() : String = System.getProperty(JAVA_SPEC_VERSION)

    fun getJavaSpecVendor() : String = System.getProperty(JAVA_SPEC_VENDOR)

    fun getJavaSpecName() : String = System.getProperty(JAVA_SPEC_NAME)

    fun getVmSpecVendor() : String = System.getProperty(VM_SPEC_VENDOR)

    fun getVmSpecName() : String = System.getProperty(VM_SPEC_NAME)

    fun getVmVersion() : String = System.getProperty(VM_VERSION)

    fun getVmVendor() : String = System.getProperty(VM_VENDOR)

    fun getVmName() : String = System.getProperty(VM_NAME)

    fun getUserLanguage() : String = System.getProperty(USER_LANGUAGE)

    fun getUserHome() : String = System.getProperty(USER_NAME)

    fun getJavaTempDir() : String = System.getProperty(JAVA_IO_TMPDIR)

    fun getUserDir() : String = System.getProperty(USER_DIR)

    fun getUserName() : String = System.getProperty(USER_NAME)

    fun getJavaHome() : String = System.getProperty(JAVA_HOME)

    fun getAwtHeadless() : String = System.getProperty(AWT_HEADLESS)

    fun getAwtUseSystemAAFontSettings() : String = System.getProperty(AWT_AA_FONT_SETTINGS)

}