package com.example.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionInterpolator
import androidx.constraintlayout.widget.ConstraintSet.Motion
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    var isDragging = false
    var maxLeft =0f
    var maxRight = 0f
    var maxTop = 0f
    var maxBottom =0f
    var dx = 0f
    var dy = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toggleView = findViewById<View>(R.id.toggle_view)
        val card = findViewById<View>(R.id.big_view)

        fun  updateParentBounds(){
            maxLeft = 0f
            maxRight = maxLeft + card.width.toFloat() + toggleView.width

            maxTop = 0f
            maxBottom = maxTop + card.height.toFloat()+toggleView.height
        }
        card.setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val centerX =  card?.width!!/2f -50f
                val centerY = card?.height!!/2f -50f

                val bounds = FloatArray(4)

                if(isDragging){
//                bounds[0] = event?.x!!-50f
//                if(bounds[0] < maxLeft){
//                    bounds[0] = maxLeft
//                }
//
//                bounds[2] = bounds[0]
//                if(bounds[2] > maxRight){
//                    bounds[2] = maxRight
//                    bounds[0] = bounds[2]
//                }
//                bounds[1] = event.y-50f
//                if (bounds[1] < maxTop) {
//                    bounds[1] = maxTop;
//                }
//                bounds[3] = bounds[1]
//                if (bounds[3] > maxBottom) {
//                    bounds[3] = maxBottom;
//                    bounds[1] = bounds[3]
//                }
                    var abs = Math.sqrt(((toggleView.x - centerX) * (toggleView.x - centerX) + (toggleView.y - centerY) *(toggleView.y - centerY)).toDouble())
                    if(abs > card.width/2f){
                        toggleView.x = ((toggleView.x - centerX) * (card.width/2f) / abs + centerX).toFloat()
                        toggleView.y = ((toggleView.y - centerY) * (card.width/2f) / abs + centerY).toFloat()


                    }

                when(event?.action){



                        MotionEvent.ACTION_MOVE -> {
                            var inX = event.x >= card.x && event.x <=  card.x + card.width
                            var inY = event.y >= card.y && event.y <= card.y + card.height



                            if(inX && inY) {
                                    toggleView.animate()
                                        .x(event?.x!! - 50f)
                                        .y(event.y - 50f)
                                        .setDuration(0)
                                        .start();

                            }
                            return true

                        }
                    MotionEvent.ACTION_UP -> {
                        toggleView.animate()
                            .x(centerX)
                            .y(centerY)
                            .setDuration(0)
                            .start()
                        isDragging = false

                        return false
                    }
                       else ->
                            return true

                    }
                }
                else{
                    when(event?.action){
                        MotionEvent.ACTION_DOWN -> {
                            var inX = event.x >= toggleView.x && event.x <= toggleView.x + toggleView.width
                            var inY = event.y >= toggleView.y && event.y <= toggleView.y + toggleView.height
                            Log.d("touch",inX.toString())
                            if(inX && inY ) {
                                isDragging = true;
                                updateParentBounds()
                                dx = v?.x!! - event.x
                                dy = v?.y!! - event.y
                                return true
                            }else{
                                return false
                            }
                        }
                        MotionEvent.ACTION_UP -> {
                            toggleView.animate()
                                .x(centerX)
                                .y(centerY)
                                .setDuration(0)
                                .start()
                            isDragging = false

                            return false
                        }
                        else ->
                           return false
                    }

                }
                return true
                }
            })

        }
    fun onDragFinish(){


        isDragging = false
    }
    }
