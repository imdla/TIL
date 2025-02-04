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

// 서울의 측정 정보를 배열 -> 객체로 바꾸기
// key는 stationName
function minDustStation(arrDust) {
  let objDust = {};

  for (let dust of arrDust) {
    let dustStationName = dust.stationName;
    objDust[dustStationName] = dust;
  }

  return objDust;
}

async function main() {
  const arrDust = await getPost();
  const objDust = minDustStation(arrDust);

  console.log(objDust);
}

main();

// 서울의 데이터를 stationName으로 접근하기 쉬운 자료구조 -> object
