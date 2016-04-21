
object readCSV {

    def parseCSV(csvfile: String): Iterator[Map[String,String]] = {
        val bufferedSource = io.Source.fromFile(csvfile)
	val lines = bufferedSource.getLines
        val header = lines.next.split(",").map(_.trim)

        for (line <- lines if lines.hasNext) yield {
	    if (lines.isEmpty) { bufferedSource.close }
	    val dataMap = (header zip line.split(",").map(_.trim)).toMap
	    dataMap
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
