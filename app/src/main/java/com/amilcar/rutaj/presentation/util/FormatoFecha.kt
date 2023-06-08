package com.amilcar.rutaj.presentation.util

import java.time.format.DateTimeFormatter


fun formatoFecha(dateString: String) :String{


    //val date = LocalDate.parse(
     //   "2023-01-15", DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    print(dateString.format(formatter))

    //val dateString = "1 January, 2018"
    //val dateString = "1 January, 2018"

    //val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    //val date = LocalDate.parse(trim(dateString), formatter)

  //  println(date1)


//    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    //val dateformatted = " "
// LocalDate.parse("2018-12-31", formatter).toString()

      //  Log.d("mica conv", dateformatted)
    return ""
}