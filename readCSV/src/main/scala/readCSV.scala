
object readCSV {

    def parseCSV(csvfile: String): Iterator[String] = {
        val bufferedSource = io.Source.fromFile(csvfile)

	for (line <- bufferedSource.getLines) yield line
	
	//bufferedSource.close
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
