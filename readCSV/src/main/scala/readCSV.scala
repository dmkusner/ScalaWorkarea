
import scala.language.reflectiveCalls

object readCSV {

    def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
        try {
            f(resource)
        } finally {
            resource.close()
        }

    def parseCSV(csvfile: String): Iterator[Map[String,String]] = {
        using(io.Source.fromFile(csvfile)) { bufferedSource =>
	    val lines = bufferedSource.getLines
            val header = lines.next.split(",").map(_.trim)
	    val splitLines = lines.map(_.split(",").map(_.trim))
	    val (it1,it2) = splitLines.duplicate
	    val num = it1.length
	    
	    val iter = for (i <- (1 to num).toIterator) yield {
	    	val line = it2.next
	    	val dataMap = (header zip line).toMap
		dataMap
	    }
	    iter
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
