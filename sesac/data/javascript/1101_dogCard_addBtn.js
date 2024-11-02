// 강아지 사진 url 가져오기
async function getDogImgUrl() {
  let url = `https://dog.ceo/api/breeds/image/random`;

  const response = await fetch(url);
  const data = await response.json();
  const dogImgUrl = data.message;

  return dogImgUrl;
}

// 카드 만들기
function makeAddCard() {
  const addBtn = document.querySelector('.addBtn');

  addBtn.addEventListener('click', async () => {
    await makeBasicCard(1);
    const cardLast = document.querySelector('.dogCard:last-child');
    cardLast.classList.remove('hidden');
  });
}

// 기본 카드 만들기
async function makeBasicCard(num) {
  for (let i = 0; i < num; i++) {
    let dogImgUrl = await getDogImgUrl();
    const card = document.querySelector('.dogCard');
    const cardBox = document.querySelector('.cardBox');

    const cloneCard = card.cloneNode(true);
    cloneCard.querySelector('.dogCardImg').setAttribute('src', dogImgUrl);

    cardBox.append(cloneCard);
  }

  const card_1 = document.querySelector('.dogCard:first-child');
  card_1.classList.add('hidden');
}

function main() {
  makeBasicCard(6);
  makeAddCard();
}

main();
