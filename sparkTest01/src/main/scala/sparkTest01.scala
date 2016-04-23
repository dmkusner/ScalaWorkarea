
import scala.io.Source
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.Row

object sparkTest01 {

    def parseCSV(csvfile: String): Iterator[Map[String,String]] = {
        val bufferedSource = Source.fromFile(csvfile)
	val lines = bufferedSource.getLines
        val header = lines.next.split(",").map(_.trim)
	val splitLines = lines.map(_.split(",").map(_.trim))

	for (line <- splitLines) yield {
            val dataMap = (header zip line).toMap
            dataMap
	}
    }

    def main(args: Array[String]) {
        val csvFiles = Array(
	    "/media/sf_VBoxShared/csv/data_random_million_rows_0.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_1.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_2.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_3.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_4.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_5.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_6.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_7.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_8.csv",
	    "/media/sf_VBoxShared/csv/data_random_million_rows_9.csv"
	)
	
    	val conf = new SparkConf().setAppName("sparkTest01")
    	val sc = new SparkContext(conf)

	val csvData = sc.parallelize(csvFiles,4)
	val rowData = csvData.flatMap(parseCSV)

	val sample = rowData.take(5)
	sample.foreach(println)
    }

}
