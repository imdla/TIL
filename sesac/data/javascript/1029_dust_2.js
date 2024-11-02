import config from '../../apikey';

let url =
  'http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty';
let queryParams =
  '?' + encodeURIComponent('serviceKey') + config.DUST_API_KEY; /* Service Key*/
queryParams +=
  '&' +
  encodeURIComponent('returnType') +
  '=' +
  encodeURIComponent('json'); /* */
queryParams +=
  '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('100'); /* */
queryParams +=
  '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /* */
queryParams +=
  '&' + encodeURIComponent('sidoName') + '=' + encodeURIComponent('서울'); /* */
queryParams +=
  '&' + encodeURIComponent('ver') + '=' + encodeURIComponent('1.0'); /* */

// 미세먼지 서울 측정 정보 조회
async function getPost() {
  const URL = `${url}${queryParams}`;

  const response = await fetch(URL);
  const data = await response.json();
  const arrDust = data.response.body.items;

  return arrDust;
}

// 미세먼지 가장 낮은 stationName
function minDustStation(arrDust) {
  let minPm25Value = 999;
  let minDust = null;

  for (let dust of arrDust) {
    let curpm25Value = Number(dust.pm25Value);

    if (curpm25Value !== '-' && minPm25Value > curpm25Value) {
      minPm25Value = curpm25Value;
      minDust = dust;
    }
  }

  return minDust;
}

async function main() {
  const arrDust = await getPost();
  const minDust = minDustStation(arrDust);

  // 미세먼지 가장 낮은 stationName과 농도
  console.log(minDust.stationName);
  console.log(minDust.pm25Value);
}

main();

// 초 미세먼지 농도는 어떤 key값 -> pm25Value
