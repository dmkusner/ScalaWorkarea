
import scala.language.reflectiveCalls

object readCSV {

    def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
        try {
            f(resource)
        } finally {
            resource.close()
        }

    case class Record(uttdate: String, length: String)

    def parseCSV(csvfile: String): Iterator[Record] = {
        using(io.Source.fromFile(csvfile)) { bufferedSource =>
	    val lines = bufferedSource.getLines
            val header = lines.next.split(",").map(_.trim)
	    val splitLines = lines.map(_.split(",").map(_.trim))
	    val (it1,it2) = splitLines.duplicate
	    val num = it1.length
	    
	    for (i <- (1 to num).toIterator) yield {
	    	val line = it2.next
		val dataMap = (header zip line).toMap
		val record = Record(dataMap("uttdate"), dataMap("length"))
		record
	    }
	}
    }

    def main(args: Array[String]): Unit = {
        if (args.length != 1) {
	    println("Usage: readCSV csvfile")
	}
	else {
	    parseCSV(args(0)).foreach(println)
	}
    }

}
