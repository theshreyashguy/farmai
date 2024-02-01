package com.example.farmai.ui.dashboard

import android.app.Application
import android.app.usage.UsageStatsManager
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmai.ApiInterface
import com.example.farmai.Data
import com.example.farmai.R
import com.example.farmai.RetrofitClient
import com.example.farmai.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var Farm : Data




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val soil = root.findViewById<Button>(R.id.Soil)
        val card1 = root.findViewById<CardView>(R.id.card1)
        val rain = root.findViewById<Button>(R.id.Rain)
        val card2 = root.findViewById<CardView>(R.id.card2)
        val temperature = root.findViewById<Button>(R.id.temperature)
        val card3 = root.findViewById<CardView>(R.id.card3)
        val humidity = root.findViewById<Button>(R.id.humidity)
        val card4 = root.findViewById<CardView>(R.id.card4)
        val pump = root.findViewById<Button>(R.id.pump)
        val card5 = root.findViewById<CardView>(R.id.card5)
        val tempyes = root.findViewById<Button>(R.id.tempyes)
        val tempif = root.findViewById<Button>(R.id.tempid)

        tempyes.setOnClickListener {
            tempyes.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            tempif.background = resources.getDrawable(R.drawable.rounded_corner_button)
            tempyes.setTextColor(Color.WHITE)
            tempif.setTextColor(Color.BLACK)
            root.findViewById<LineChart>(R.id.barChart2).visibility = View.VISIBLE
            root.findViewById<TextView>(R.id.tempval1).visibility = View.GONE
        }
        tempif.setOnClickListener {
            tempyes.background = resources.getDrawable(R.drawable.rounded_corner_button)
            tempif.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            tempyes.setTextColor(Color.WHITE)
            tempif.setTextColor(Color.BLACK)
            root.findViewById<LineChart>(R.id.barChart2).visibility = View.GONE
            root.findViewById<TextView>(R.id.tempval1).visibility = View.VISIBLE
        }


        soil.setOnClickListener {
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            rain.background = resources.getDrawable(R.drawable.rounded_corner_button)
            temperature.background = resources.getDrawable(R.drawable.rounded_corner_button)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button)
            pump.background = resources.getDrawable(R.drawable.rounded_corner_button)
            soil.setTextColor(Color.WHITE)
            rain.setTextColor(Color.BLACK)
            temperature.setTextColor(Color.BLACK)
            humidity.setTextColor(Color.BLACK)
            pump.setTextColor(Color.BLACK)
            card1.visibility = View.VISIBLE
            card2.visibility = View.GONE
            card3.visibility = View.GONE
            card4.visibility = View.GONE
            card5.visibility = View.GONE
        }
        rain.setOnClickListener {
//            soil.setTextColor(1)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button)
            rain.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            temperature.background = resources.getDrawable(R.drawable.rounded_corner_button)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button)
            pump.background = resources.getDrawable(R.drawable.rounded_corner_button)
            soil.setTextColor(Color.BLACK)
            rain.setTextColor(Color.WHITE)
            temperature.setTextColor(Color.BLACK)
            humidity.setTextColor(Color.BLACK)
            pump.setTextColor(Color.BLACK)
            card1.visibility = View.GONE
            card2.visibility = View.VISIBLE
            card3.visibility = View.GONE
            card4.visibility = View.GONE
            card5.visibility = View.GONE
        }
        temperature.setOnClickListener {
//            soil.setTextColor(1)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button)
            rain.background = resources.getDrawable(R.drawable.rounded_corner_button)
            temperature.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button)
            pump.background = resources.getDrawable(R.drawable.rounded_corner_button)
            soil.setTextColor(Color.BLACK)
            rain.setTextColor(Color.BLACK)
            temperature.setTextColor(Color.WHITE)
            humidity.setTextColor(Color.BLACK)
            pump.setTextColor(Color.BLACK)
            card1.visibility = View.GONE
            card2.visibility = View.GONE
            card3.visibility = View.VISIBLE
            card4.visibility = View.GONE
            card5.visibility = View.GONE
        }
        humidity.setOnClickListener {
//            soil.setTextColor(1)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button)
            rain.background = resources.getDrawable(R.drawable.rounded_corner_button)
            temperature.background = resources.getDrawable(R.drawable.rounded_corner_button)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            pump.background = resources.getDrawable(R.drawable.rounded_corner_button)
            soil.setTextColor(Color.BLACK)
            rain.setTextColor(Color.BLACK)
            temperature.setTextColor(Color.BLACK)
            humidity.setTextColor(Color.WHITE)
            pump.setTextColor(Color.BLACK)
            card1.visibility = View.GONE
            card2.visibility = View.GONE
            card3.visibility = View.GONE
            card4.visibility = View.VISIBLE
            card5.visibility = View.GONE
        }
        pump.setOnClickListener {
//            soil.setTextColor(1)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button)
            rain.background = resources.getDrawable(R.drawable.rounded_corner_button)
            temperature.background = resources.getDrawable(R.drawable.rounded_corner_button)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button)
            pump.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            soil.setTextColor(Color.BLACK)
            rain.setTextColor(Color.BLACK)
            temperature.setTextColor(Color.BLACK)
            humidity.setTextColor(Color.BLACK)
            pump.setTextColor(Color.WHITE)
            card1.visibility = View.GONE
            card2.visibility = View.GONE
            card3.visibility = View.GONE
            card4.visibility = View.GONE
            card5.visibility = View.VISIBLE
        }



        val change = root.findViewById<Button>(R.id.change)
        change.setOnClickListener {
            val im1 = root.findViewById<GifImageView>(R.id.im1)
            val im2 = root.findViewById<GifImageView>(R.id.im2)
            if(im1.visibility == View.GONE){
                im1.visibility = View.VISIBLE
                im2.visibility = View.GONE

            }
            else{
                im1.visibility = View.GONE
                im2.visibility = View.VISIBLE
            }
        }


        setup(root)
        temp(root)

        return root
    }

    fun temp(root: View){
        val temperatureChart: LineChart = root.findViewById(R.id.barChart2)

        // Sample temperature data for the day
        val temperatureData = listOf(
            Entry(0f, 12f),
            Entry(1f, 13f),
            Entry(2f, 14f),
            Entry(3f, 15f),
            Entry(4f, 16f),
            Entry(5f, 17f),
            Entry(6f, 18f),
            Entry(7f, 19f),
            Entry(8f, 20f),
            Entry(9f, 21f),
            Entry(10f, 26f),
            Entry(11f, 23f),
            Entry(12f, 26f),
            Entry(13f, 21f),
            Entry(14f, 20f),
            Entry(15f, 19f),
            Entry(16f, 18f),
            Entry(17f, 16f),
            Entry(18f, 14f),
            Entry(19f, 12f),
            Entry(20f, 10f),
            Entry(21f, 14f),
            Entry(22f, 15f),

        )

        // Create a LineDataSet
        val dataSet = LineDataSet(temperatureData, "Temperature")
        dataSet.color = Color.BLUE
        dataSet.setCircleColor(Color.BLUE)

        // Create a LineData object and set the data set
        val lineData = LineData(dataSet)

        // Set data to the chart
        temperatureChart.data = lineData

        // Customize chart appearance and behavior
        val description = Description()
        description.text = "Temperature Throughout the Day"
        temperatureChart.description = description

        // Customize X-axis
        val xAxis: XAxis = temperatureChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // Customize Y-axis
        val leftYAxis: YAxis = temperatureChart.axisLeft
        leftYAxis.textColor = Color.BLACK

        // Remove right Y-axis
        temperatureChart.axisRight.isEnabled = false

        // Customize legend
        val legend: Legend = temperatureChart.legend
        legend.form = Legend.LegendForm.LINE
        legend.textColor = Color.BLACK
        // Refresh the chart
        temperatureChart.invalidate()
    }

    fun sol(root: View){
        val barChart = root.findViewById<BarChart>(R.id.barChart)

// Initialize the BarChart
        barChart.description.isEnabled = false
        barChart.setDrawGridBackground(false)



// Prepare data for the chart
        val entries = ArrayList<BarEntry>()
        val xLabels = ArrayList<String>() // List for X-axis labels

        val x = 100
        val z = Farm.Soilmoisture
        entries.add(BarEntry(x.toFloat(),z.toFloat()))
        xLabels.add("Soil Moisture") // Add package name to X-axis labels

        val dataSet = BarDataSet(entries, "Soil Moisture")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val data = BarData(dataSet)
        barChart.data = data

// Further customization
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.textColor = Color.BLACK
        xAxis.textSize = 12f
        xAxis.typeface = Typeface.DEFAULT_BOLD
        xAxis.valueFormatter = IndexAxisValueFormatter(xLabels.toTypedArray()) // Set X-axis labels

// Customize Y-axis
        val yAxisLeft = barChart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.textColor = Color.BLACK
        yAxisLeft.textSize = 12f
        yAxisLeft.typeface = Typeface.DEFAULT_BOLD

// Customize legend
        val legend = barChart.legend
        legend.form = Legend.LegendForm.SQUARE
        legend.textSize = 12f
        legend.textColor = Color.BLACK
        legend.typeface = Typeface.DEFAULT_BOLD

// Set animation
        barChart.animateY(1000)

// To write the package name on the bars, enable value text
        dataSet.setDrawValues(true)
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTypeface = Typeface.DEFAULT_BOLD

// Further customization can be done here


        barChart.invalidate()
    }

    fun hum(root: View){
        val barChart = root.findViewById<BarChart>(R.id.barChart1)

// Initialize the BarChart
        barChart.description.isEnabled = false
        barChart.setDrawGridBackground(false)



// Prepare data for the chart
        val entries = ArrayList<BarEntry>()
        val xLabels = ArrayList<String>() // List for X-axis labels

        val x = 100
        val z = Farm.Humidity
        entries.add(BarEntry(x.toFloat(),z.toFloat()))
        xLabels.add("Humidity") // Add package name to X-axis labels

        val dataSet = BarDataSet(entries, "Soil Moisture")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val data = BarData(dataSet)
        barChart.data = data

// Further customization
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.textColor = Color.BLACK
        xAxis.textSize = 12f
        xAxis.typeface = Typeface.DEFAULT_BOLD
        xAxis.valueFormatter = IndexAxisValueFormatter(xLabels.toTypedArray()) // Set X-axis labels

// Customize Y-axis
        val yAxisLeft = barChart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.textColor = Color.BLACK
        yAxisLeft.textSize = 12f
        yAxisLeft.typeface = Typeface.DEFAULT_BOLD

// Customize legend
        val legend = barChart.legend
        legend.form = Legend.LegendForm.SQUARE
        legend.textSize = 12f
        legend.textColor = Color.BLACK
        legend.typeface = Typeface.DEFAULT_BOLD

// Set animation
        barChart.animateY(1500)

// To write the package name on the bars, enable value text
        dataSet.setDrawValues(true)
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTypeface = Typeface.DEFAULT_BOLD

// Further customization can be done here



        barChart.invalidate()
    }


    fun setup(root: View){
        val apiService = RetrofitClient.instance.create(ApiInterface::class.java)
        val call = apiService.getData()

        call.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    val dataList = response.body()
                    if (dataList != null) {
                        // Process the list of Data objects
                        for (data in dataList) {
                           if(data._id=="650156153c813771bfbe06e7"){
                               Farm = data
                           }
                        }
                        Log.i("Data",Farm.toString())
                        hum(root)
                        sol(root)
                        root.findViewById<TextView>(R.id.tempval1).setText(Farm.Temperature[0])
                        if(Farm.Raindrops.equals("1")){
                            val im1 = root.findViewById<GifImageView>(R.id.sunny)
                            val im2 = root.findViewById<GifImageView>(R.id.notsunny)
                            im1.visibility = View.GONE
                            im2.visibility = View.VISIBLE

                        }
                        else{
                            val im1 = root.findViewById<GifImageView>(R.id.sunny)
                            val im2 = root.findViewById<GifImageView>(R.id.notsunny)
                            im1.visibility = View.VISIBLE
                            im2.visibility = View.GONE
                        }
                    }
                } else {
                    // Handle error
                    Log.i("Data12",response.toString())
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                // Handle network errors
                Log.i("Data",t.toString())
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}