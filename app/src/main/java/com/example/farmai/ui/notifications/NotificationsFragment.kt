package com.example.farmai.ui.notifications

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmai.R
import com.example.farmai.databinding.FragmentNotificationsBinding
import com.example.farmai.ml.Autoencoder
import com.example.farmai.ml.CropRecommendation
import com.example.farmai.ml.NeuralNetworkModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import pl.droidsonroids.gif.GifImageView
import java.nio.ByteBuffer
import java.nio.ByteOrder

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var phos : EditText
    private lateinit var nitro : EditText
    private lateinit var k : EditText
    private lateinit var phos1 : EditText
    private lateinit var nitro1 : EditText
    private lateinit var k1 : EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val anamoly = root.findViewById<Button>(R.id.anamoly)
        val card1 = root.findViewById<CardView>(R.id.card1)
        val nutrient = root.findViewById<Button>(R.id.nutrient)
        val card2 = root.findViewById<CardView>(R.id.card2)
        val fertilizer = root.findViewById<Button>(R.id.Fertilizer)
        val card3 = root.findViewById<CardView>(R.id.card3)
        nitro = root.findViewById<AppCompatEditText>(R.id.nitro)
        phos  = root.findViewById<AppCompatEditText>(R.id.phos)
        k = root.findViewById(R.id.potas)
        nitro1 = root.findViewById<AppCompatEditText>(R.id.nitro1)
        phos1  = root.findViewById<AppCompatEditText>(R.id.phos1)
        k1 = root.findViewById(R.id.potas1)


        anamoly.setOnClickListener {
            anamoly.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            nutrient.background = resources.getDrawable(R.drawable.rounded_corner_button)
            fertilizer.background = resources.getDrawable(R.drawable.rounded_corner_button)
            anamoly.setTextColor(Color.WHITE)
            nutrient.setTextColor(Color.BLACK)
            fertilizer.setTextColor(Color.BLACK)
            card1.visibility = View.VISIBLE
            card2.visibility = View.GONE
            card3.visibility = View.GONE
        }



        nutrient.setOnClickListener {
            anamoly.background = resources.getDrawable(R.drawable.rounded_corner_button)
            nutrient.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            fertilizer.background = resources.getDrawable(R.drawable.rounded_corner_button)
            anamoly.setTextColor(Color.BLACK)
            nutrient.setTextColor(Color.WHITE)
            fertilizer.setTextColor(Color.BLACK)
            card1.visibility = View.GONE
            card2.visibility = View.VISIBLE
            card3.visibility = View.GONE
        }


        fertilizer.setOnClickListener {
            anamoly.background = resources.getDrawable(R.drawable.rounded_corner_button)
            nutrient.background = resources.getDrawable(R.drawable.rounded_corner_button)
            fertilizer.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            anamoly.setTextColor(Color.BLACK)
            nutrient.setTextColor(Color.BLACK)
            fertilizer.setTextColor(Color.WHITE)
            card1.visibility = View.GONE
            card2.visibility = View.GONE
            card3.visibility = View.VISIBLE
        }


        val soil = root.findViewById<Button>(R.id.Soil)
        val temp = root.findViewById<Button>(R.id.Temperature)
        val pred = root.findViewById<Button>(R.id.predict)
        val humidity = root.findViewById<Button>(R.id.humidity)

        temp.setOnClickListener {
            temp.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button)
            temp.setTextColor(Color.WHITE)
            soil.setTextColor(Color.BLACK)
            humidity.setTextColor(Color.BLACK)
            pred.visibility = View.VISIBLE
            root.findViewById<GifImageView>(R.id.resyes).visibility = View.GONE
            root.findViewById<GifImageView>(R.id.resno).visibility = View.GONE
        }
        soil.setOnClickListener {
            temp.background = resources.getDrawable(R.drawable.rounded_corner_button)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button1)
            humidity.background = resources.getDrawable(R.drawable.rounded_corner_button)
            temp.setTextColor(Color.BLACK)
            soil.setTextColor(Color.WHITE)
            humidity.setTextColor(Color.BLACK)
            pred.visibility = View.GONE
            root.findViewById<GifImageView>(R.id.resyes).visibility = View.GONE
            root.findViewById<GifImageView>(R.id.resno).visibility = View.GONE
        }

        humidity.setOnClickListener {
            temp.background = resources.getDrawable(R.drawable.rounded_corner_button)
            soil.background = resources.getDrawable(R.drawable.rounded_corner_button)
            humidity.background=resources.getDrawable(R.drawable.rounded_corner_button1)
            temp.setTextColor(Color.BLACK)
            soil.setTextColor(Color.BLACK)
            humidity.setTextColor(Color.WHITE)
            pred.visibility = View.GONE
            root.findViewById<GifImageView>(R.id.resyes).visibility = View.GONE
            root.findViewById<GifImageView>(R.id.resno).visibility = View.GONE
        }


        pred.setOnClickListener {
            predict(root)
        }
        root.findViewById<Button>(R.id.predict1).setOnClickListener {
            pred1(root)
        }
        root.findViewById<Button>(R.id.predict11).setOnClickListener {
            pred11(root)
        }

       // pred1()


      //  predict()


        return root
    }

    fun pred1(root: View){

        val inputBuffer = ByteBuffer.allocateDirect(4 * 7) // Adjust size according to input features

        // Add the input data
        // Example user inputs
        val N = nitro.text.toString().toFloat()
        val P = phos.text.toString().toFloat()
        val K = k.text.toString().toFloat()
        val temperature = 30.0f
        val humidity = 20.0f
        val pH = 6.5f
        val rainfall = 100.0f

        inputBuffer.putFloat(N)
        inputBuffer.putFloat(P)
        inputBuffer.putFloat(K)
        inputBuffer.putFloat(temperature)
        inputBuffer.putFloat(humidity)
        inputBuffer.putFloat(pH)
        inputBuffer.putFloat(rainfall)


        val model =  context?.let { CropRecommendation.newInstance(it.applicationContext) }

// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 7), DataType.FLOAT32)
        inputFeature0.loadBuffer(inputBuffer)

// Runs model inference and gets result.
        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        if(outputFeature0 !=null){
            val outputBuffer = outputFeature0.floatArray

            // Assuming that the output is of type float, you can iterate through the float values.
            var maxIndex = 0
            for (i in 0 until outputBuffer.size) { // Assuming 4 bytes per float
                val outputValue = outputBuffer[i]
                if(outputValue > outputBuffer[maxIndex]){
                    maxIndex = i
                }

            }
            maxIndex = maxIndex - 1
            val cropDict = mapOf(
                1 to "Rice",
                2 to "Maize", 3 to "Jute", 4 to "Cotton", 5 to "Coconut", 6 to "Papaya", 7 to "Orange",
            8 to "Apple", 9 to "Muskmelon", 10 to "Watermelon", 11 to "Grapes", 12 to "Mango", 13 to "Banana",
            14 to "Pomegranate", 15 to "Lentil", 16 to "Blackgram", 17 to "Mungbean", 18 to "Mothbeans",
            19 to "Pigeonpeas", 20 to "Kidneybeans", 21 to "Chickpea",22 to "Coffee"
                // Add other crop names and indices
            )

            val ans =  cropDict[maxIndex] ?: "UnknownCrop"
            root.findViewById<TextView>(R.id.output).setText(cropDict[maxIndex] ?: "UnknownCrop")
            Toast.makeText(context,ans,Toast.LENGTH_LONG).show()

        }

// Releases model resources if no longer used.
        if (model != null) {
            model.close()
        }
    }



    fun pred11(root: View){

        val inputBuffer = ByteBuffer.allocateDirect(4 * 6) // Adjust size according to input features

        // Add the input data
        // Example user inputs
        val N = nitro1.text.toString().toFloat()
        val P = phos1.text.toString().toFloat()
        val K = k1.text.toString().toFloat()
        val temperature = 30.0f
        val humidity = 20.0f
        val rainfall = 100.0f

        inputBuffer.putFloat(N)
        inputBuffer.putFloat(P)
        inputBuffer.putFloat(K)
        inputBuffer.putFloat(temperature)
        inputBuffer.putFloat(humidity)
        inputBuffer.putFloat(rainfall)


        val model =  context?.let { NeuralNetworkModel.newInstance(it.applicationContext) }

// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 6), DataType.FLOAT32)
        inputFeature0.loadBuffer(inputBuffer)

// Runs model inference and gets result.
        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        if(outputFeature0 !=null){
            val outputBuffer = outputFeature0.floatArray

            // Assuming that the output is of type float, you can iterate through the float values.
            var maxIndex = 0
            for (i in 0 until outputBuffer.size) { // Assuming 4 bytes per float
                val outputValue = outputBuffer[i]
                if(outputValue > outputBuffer[maxIndex]){
                    maxIndex = i
                }

            }
            //'Urea', 'DAP', '14-35-14', '28-28', '17-17-17', '20-20',
            //       '10-26-26'
            maxIndex = maxIndex - 1
            val cropDict = mapOf(
                1 to "Urea",
                2 to "DAP", 3 to "14-35-14", 4 to "28-28", 5 to "17-17-17", 6 to "20-20", 7 to "10-26-26",
                // Add other crop names and indices
            )

            val ans =  cropDict[maxIndex] ?: "UnknownCrop"
            root.findViewById<TextView>(R.id.output11).setText(cropDict[maxIndex] ?: "Unknown")
            Toast.makeText(context,ans,Toast.LENGTH_LONG).show()

        }

// Releases model resources if no longer used.
        if (model != null) {
            model.close()
        }
    }




    fun predict (root: View){
        val temperatureArray = intArrayOf(
            21, 23, 25, 27, 29, 30, 31, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24,
            23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33,
            30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36,
            35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35,
            35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28,
            30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20,
            21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23,
            22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32,
            30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36,
            36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34,
            35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26,
            28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20,
            20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24,
            23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33,
            32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36,
            36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33,
            34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24,
            26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20,
            20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26,
            24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34,
            33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36,
            36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32,
            33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22,
            24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21,
            20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28,
            26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35,
            34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35,
            36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30,
            32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21,
            22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22,
            21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30,
            28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36,
            35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35,
            35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28,
            30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20,
            21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23,
            22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32,
            30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36,
            36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34,
            35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26,
            28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20,
            20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20,
            21, 23, 25, 27, 29, 30, 31, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24,
            23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33,
            30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36,
            35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35,
            35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28,
            30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20,
            21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23,
            22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32,
            30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36,
            36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34,
            35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26,
            28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20,
            20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24,
            23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33,
            32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33, 34, 35, 35, 36, 36,
            36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24, 26, 28, 30, 32, 33,
            34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21, 20, 20, 20, 21, 22, 24,
            26, 28, 30, 32, 33, 34, 35, 35, 36, 36, 36, 36, 35, 34, 33, 32, 30, 28, 26, 24, 23, 22, 21
        )

// Calculate the number of elements and size of each element in the array
        val numElements = temperatureArray.size
        val elementSize = Integer.SIZE / 8  // Size of one integer in bytes (4 bytes)

// Calculate the total size in bytes for the ByteBuffer
        val totalSize = numElements * elementSize

// Create a ByteBuffer with little-endian byte order (you can change this to big-endian if needed)
        val byteBuffer = ByteBuffer.allocate(totalSize).order(ByteOrder.LITTLE_ENDIAN)

// Load the temperatureArray into the ByteBuffer
        for (temperature in temperatureArray) {
            byteBuffer.putInt(temperature)
        }

// Reset the position of the ByteBuffer to its beginning
        byteBuffer.rewind()

        val model = context?.let { Autoencoder.newInstance(it.applicationContext) }

// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 1439), DataType.FLOAT32)


        inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer
        if (outputFeature0 != null) {
            val x = calculateMSE(temperatureArray,outputFeature0.floatArray)
            val threshold = 0.15211730987070476
            if(x>threshold){
                Toast.makeText(context,"Anomoaly is their",Toast.LENGTH_LONG).show()
                root.findViewById<GifImageView>(R.id.resyes).visibility = View.VISIBLE
                root.findViewById<GifImageView>(R.id.resno).visibility = View.GONE

            }
            else{
                Toast.makeText(context,"No Anomoaly",Toast.LENGTH_LONG).show()
                root.findViewById<GifImageView>(R.id.resyes).visibility = View.GONE
                root.findViewById<GifImageView>(R.id.resno).visibility = View.VISIBLE
            }
        }

// Releases model resources if no longer used.
        model?.close()
    }

    fun calculateMSE(inputData: IntArray, outputData: FloatArray): Float {
        var sum = 0f
        for (i in inputData.indices) {
            val diff = inputData[i].toFloat() - outputData[i]
            sum += diff * diff
        }
        return sum / inputData.size
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}