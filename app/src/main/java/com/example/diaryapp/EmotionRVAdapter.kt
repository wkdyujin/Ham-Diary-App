package com.example.diaryapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diaryapp.databinding.ItemEmotionsBinding

class EmotionRVAdapter(private var dataSet: ArrayList<Diary>) : RecyclerView.Adapter<EmotionRVAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemEmotionsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemEmotionsBinding = ItemEmotionsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    fun setList(newList: ArrayList<Diary>) {
        this.dataSet = newList
    }

    fun getElement(pos:Int): Diary {
        return dataSet[pos]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        var emotion = dataSet[position].emotion
        binding.itemEmotionImgIv.setImageResource(setEmotion(emotion))
        binding.date.text = "${dataSet[position].month}월 ${dataSet[position].day}일"
        binding.week.text = dataSet[position].dayOfWeek
    }

    override fun getItemCount(): Int = dataSet.size

    private fun setEmotion(emotion_name: String): Int {
        if(emotion_name == "행복해요") {
            return R.drawable.emotion_happy_img
        }
        if(emotion_name == "즐거워요") {
            return R.drawable.emotion_excited_img
        }
        if(emotion_name == "뿌듯해요") {
            return R.drawable.emotion_proud_img
        }
        if(emotion_name == "괜찮아요") {
            return R.drawable.emotion_fine_img
        }
        if(emotion_name == "화나요") {
            return R.drawable.emotion_stress_img
        }
        if(emotion_name == "걱정돼요") {
            return R.drawable.emotion_worried_img
        }
        if(emotion_name == "슬퍼요") {
            return R.drawable.emotion_sad_img
        }
        if(emotion_name == "피곤해요") {
            return R.drawable.emotion_tired_img
        }
        return -1
    }

}
