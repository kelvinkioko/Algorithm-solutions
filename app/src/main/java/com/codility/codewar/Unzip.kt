package com.codility.codewar

import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Path
import java.util.zip.ZipInputStream

fun main(args: Array<String>) {

}

@Throws(IOException::class)
fun unzipFolder(source: Path, target: Path) {
    // Put the InputStream obtained from Uri here instead of the FileInputStream perhaps?
    ZipInputStream(FileInputStream(source.toFile())).use { zis ->

        // list files in zip
        var zipEntry = zis.nextEntry
        while (zipEntry != null) {

            zipEntry = zis.nextEntry
        }
        zis.closeEntry()
    }
}