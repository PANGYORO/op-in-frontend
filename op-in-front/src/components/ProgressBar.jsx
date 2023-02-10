import React, { useEffect, useRef, useState } from 'react'




function ProgressBar({ totalStep, nowStep }) {
  const loaderValue = useRef(new Animated.Value(0)).current;

  const load = (count) => {
    Animated.timing(loaderValue, {
      toValue: (count / totalStep) * 100,
      duration: 500,
      useNativeDriver: false,
    }).start();
  };

  const width = loaderValue.interpolate({
    inputRange: [0, 100],
    outputRange: ["0%", "100%"],
    extrapolate: "clamp"
  });

  useEffect(() => {
    load(nowStep)
  }, [nowStep]);

  return (
    <View>
     <View style={styles.bar}>
      <Animated.View
        style={{
          backgroundColor: "#AAC9CE",
          width,
          height: 3,
          borderTopRightRadius:2,
          borderBottomRightRadius:2
        }} />
      </View> 
      <Text style={styles.step}>{nowStep}/{totalStep}</Text>
    </View>
    
  )
}
const styles = StyleSheet.create({
  bar: {
    width: '100%',
    height: 1,
    backgroundColor: '#F0F0F0'
  },
  step: {
    color: '#AAC9CE',
    fontWeight: '400',
    fontSize: 22,
    padding: 22,
    lineHeight: 22 * 1.3,
    textAlign: 'center'
  }
});
export default ProgressBar
