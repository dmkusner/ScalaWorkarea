
object readCSV {

    def printCSV(csvfile: String): Unit = {
        val bufferedSource = io.Source.fromFile(csvfile)
	for (line <- bufferedSource.getLines) println(line)
	bufferedSource.close
    }

    def parseCSV(csvfile: String): Iterator[Map[String,String]] = {
        val bufferedSource = io.Source.fromFile(csvfile)
	val lines = bufferedSource.getLines
        val header = lines.next.split(",").map(_.trim)
	for (line <- lines) yield (header zip line.split(",").map(_.trim)).toMap
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
