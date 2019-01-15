import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = ""
typeMapping = [
        (~/(?i)bigint/)                           : "Long",
        (~/(?i)int|tinyint/)                      : "Integer",
        (~/(?i)float|double|decimal|real/)        : "Double",
        (~/(?i)datetime|timestamp|date|time/)     : "Date",
        (~/(?i)/)                                 : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    packageName = getPackageName(dir)
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, className + ".java")), "UTF-8"))
    printWriter.withPrintWriter {out -> generate(out, className, fields,table)}
}

// 获取包所在文件夹路径
def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "") + ";"
}

def generate(out, className, fields, table) {
    out.println "package $packageName"
    out.println ""
    out.println "import lombok.Data;"
    Set types = new HashSet()

    fields.each() {
        types.add(it.type)
    }

    if (types.contains("Date")) {
        out.println ""
        out.println "import java.util.Date;"
    }

    out.println ""
    out.println "/**\n" +
            " * ${table}  \n" +
            " */"
    out.println "@Data"
    out.println "public class $className {"
    out.println ""
    fields.each() {// 输出注释
        if (isNotEmpty(it.commoent)) {
            out.println "\t/**"
            out.println " \t* ${it.commoent.toString()}"
            out.println " \t*/"
        }
        if (it.annos != "") out.println "  ${it.annos}"
        out.println "\tprivate ${it.type} ${it.name};"
    }
    out.println ""
//  fields.each() {
//    out.println ""
//    out.println "  public ${it.type} get${it.name.capitalize()}() {"
//    out.println "    return ${it.name};"
//    out.println "  }"
//    out.println ""
//    out.println "  public void set${it.name.capitalize()}(${it.type} ${it.name}) {"
//    out.println "    this.${it.name} = ${it.name};"
//    out.println "  }"
//    out.println ""
//  }
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())

        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name : javaName(col.getName(), false),
                           type : typeStr,
                           commoent: col.getComment(),
                           annos: ""]]
    }
}

def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
