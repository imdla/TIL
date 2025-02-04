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
  '&' + encodeURIComponent('sidoName') + '=' + encodeURIComponent('전국'); /* */
queryParams +=
  '&' + encodeURIComponent('ver') + '=' + encodeURIComponent('1.0'); /* */

// 전국의 데이터 추출
async function getPost() {
  const URL = `${url}${queryParams}`;

  const response = await fetch(URL);
  const data = await response.json();
  const dust = data.response;

  console.log(dust.body);
}

getPost();

// query parameter에 넣을 수 있는 이름 -> sidoName : 전국
