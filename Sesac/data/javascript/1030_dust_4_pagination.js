import fs from 'fs';
import _ from 'lodash';

// 미세먼지 종로구 1개월 자료 추출
function getPost() {
  const dustData = fs.readFileSync('./1030_dustData.json');
  let arrDust = JSON.parse(dustData);

  arrDust = arrDust.filter((dust) => {
    return dust.pm25Value !== '-' && dust.pm10Value !== '-';
  });
  return arrDust;
}

// 종로구의 날짜에 해당하는 pm25Value, pm10Value 값
function getMonthPost(arrDust) {
  let monthData = {};

  // 일마다 pm25Value, pm10Value의 값들 배열로 할당
  for (let dust of arrDust) {
    let dataTime = `${dust.dataTime.slice(0, 10)}`;
    let pm25Value = parseInt(dust.pm25Value);
    let pm10Value = parseInt(dust.pm10Value);
    let allData = {};

    if (Object.keys(monthData).includes(dataTime)) {
      monthData[dataTime]['pm25Value'].push(pm25Value);
      monthData[dataTime]['pm10Value'].push(pm10Value);
    } else {
      allData['pm25Value'] = [pm25Value];
      allData['pm10Value'] = [pm10Value];
      monthData[dataTime] = allData;
    }
  }

  // 일마다 pm25Value, pm10Value의 평균
  for (let day in monthData) {
    let oneDay = monthData[day];

    for (let val in oneDay) {
      let value = oneDay[val];
      value = _.floor(_.mean(value));
      oneDay[val] = value;
    }

    console.log(day[0]);
    console.log(oneDay);
  }

  return monthData;
}

function main() {
  const arrDust = getPost();
  const monthData = getMonthPost(arrDust);

  console.log(monthData);
}

main();
