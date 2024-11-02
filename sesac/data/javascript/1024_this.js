const person = {
  name: 'John',
  friends: ['Jane', 'Mike'],
  city: 'Seoul',

  greet() {
    console.log(`안녕하세요. 저의 이름은 ${this.name} 입니다.`);
  },

  printFriends() {
    this.friends.forEach((friend) => {
      console.log(this.name + ' knows ' + friend); // 정상 작동
    });
  },

  living() {
    const live = () => {
      console.log(`안녕하세요. 저는 ${this.city}에 거주합니다.`);
    };
    live();
  },
};

person.greet();
person.printFriends();
person.living();
