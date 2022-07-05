package com.example.madinaapp.ui
import android.app.DatePickerDialog
import android.app.appsearch.AppSearchBatchResult
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.example.madinaapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*


class AddBottomSheet_Fragment : BottomSheetDialogFragment() {

    lateinit var startCard:CardView
    lateinit var endCard:CardView
    lateinit var endDateTxt:TextView
    lateinit var startDateTxt:TextView
    lateinit var calendar:Calendar
    lateinit var confBtn:AppCompatButton
    var date:String ?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container,false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initViews()
        setStartDatelistener()
        setEndDatelistener()
        setconBtnListener()



    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun initViews(){

        startCard=requireView().findViewById(R.id.start_date_card)
       endCard=requireView().findViewById(R.id.end_date_card)
        startDateTxt=requireView().findViewById(R.id.start_date)
        confBtn=requireView().findViewById(R.id.date_conf_btn)
        endDateTxt=requireView().findViewById(R.id.end_date)
        calendar= Calendar.getInstance()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showeDatePicker(myView:TextView){

        val myFormat ="dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val c= Calendar.getInstance()
        val view =myView

      val dataSetListener= object : DatePickerDialog.OnDateSetListener {

          override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            c.set(Calendar.YEAR, year)
             c.set(Calendar.MONTH, month)
             c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            myView.setText(sdf.format(c.time))
          }

      }

       val dataPicker=DatePickerDialog(requireContext(),dataSetListener,c.get(Calendar.YEAR),
      c.get( Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))

      dataPicker.show()
    }

 @RequiresApi(Build.VERSION_CODES.N)
 fun setStartDatelistener(){


    startCard.setOnClickListener {
        showeDatePicker(startDateTxt)


    }
 }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setEndDatelistener(){

        endCard.setOnClickListener {
           showeDatePicker(endDateTxt)
        }
    }
    fun setconBtnListener(){

      confBtn.setOnClickListener{

          this.dismiss()
      }



    }
}