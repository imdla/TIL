// 랜덤 수 가져오기
function getRandomInt(max) {
  let randomNum = Math.floor(Math.random() * max);
  return randomNum;
}

// 강아지 리스트의 품종 가져오기
async function getDogList() {
  const url = 'https://dog.ceo/api/breeds/list/all';

  const response = await fetch(url);
  const data = await response.json();
  const dogList = data.message;
  const arrDogList = Object.keys(dogList);

  return arrDogList;
}

// 강아지 사진 url 가져오기
async function getDogImgUrl(arrDogList) {
  let randomNum = getRandomInt(100);
  let dogListOne = arrDogList[randomNum];

  let url = `https://dog.ceo/api/breed/${dogListOne}/images/random`;

  const response = await fetch(url);
  const data = await response.json();
  const dogImgUrl = data.message;

  return dogImgUrl;
}

async function main() {
  const dogImgTags = document.querySelectorAll('.dogImg');

  for (let dogImgTag of dogImgTags) {
    const arrDogList = await getDogList();
    const dogImgUrl = await getDogImgUrl(arrDogList);
    dogImgTag.setAttribute('src', dogImgUrl);
  }
}

main();
