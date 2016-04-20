
object readCSV {

    def printCSV(csvfile: String): Unit = {
        val bufferedSource = io.Source.fromFile(csvfile)
	for (line <- bufferedSource.getLines) println(line)
	bufferedSource.close
    }

    def parseCSV(csvfile: String): Iterator[Array[String]] = {
        val bufferedSource = io.Source.fromFile(csvfile)
	for (line <- bufferedSource.getLines) yield line.split(",").map(_.trim)
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
