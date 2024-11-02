import config from '../../apikey';

let url =
  'http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty';
let queryParams =
  '?' + encodeURIComponent('serviceKey') + config.DUST_API_KEY; /* Service Key*/
queryParams +=
  '&' +
  encodeURIComponent('returnType') +
  '=' +
  encodeURIComponent('json'); /* */
queryParams +=
  '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('700'); /* */
queryParams +=
  '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /* */
queryParams +=
  '&' +
  encodeURIComponent('stationName') +
  '=' +
  encodeURIComponent('종로구'); /* */
queryParams +=
  '&' +
  encodeURIComponent('dataTerm') +
  '=' +
  encodeURIComponent('MONTH'); /* */
queryParams +=
  '&' + encodeURIComponent('ver') + '=' + encodeURIComponent('1.0'); /* */

// 미세먼지 종로구 1개월 자료 추출
async function getPost() {
  const URL = `${url}${queryParams}`;

  const response = await fetch(URL);
  const data = await response.json();
  const arrDust = data.response.body.items;

  return arrDust;
}

// 종로구의 날짜별 자료 배열로 추출
function getMonthPost(arrDust) {
  let arrMonth = [];
  for (let dust of arrDust) {
    if (dust.dataTime.slice(11, 13) === '12') {
      // dustDate = dust.dataTime.slice(0, 10);
      // console.log(dustDate);

      arrMonth.push(dust);
    }
  }

  return arrMonth;
}

// pm10Value, pm25Value의 1달치 데이터를 객체로 정리
function jongroDust(arrMonth) {
  let objJongro = {};

  for (let dust of arrMonth) {
    let objDust = {};
    let dustDataTime = dust.dataTime;
    let dustPm10Value = dust.pm10Value;
    let dustPm25Value = dust.pm25Value;

    objDust['pm10Value'] = dustPm10Value;
    objDust['pm25Value'] = dustPm25Value;
    objJongro[dustDataTime] = objDust;
  }

  return objJongro;
}

async function main() {
  const arrDust = await getPost();
  const arrMonth = getMonthPost(arrDust);
  const objJongro = jongroDust(arrMonth);

  console.log(objJongro);
}

main();

// 1달치 데이터 -> dataTerm = MONTH
