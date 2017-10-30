class Events {

    constructor() {
        this.events = new EventSource('');

        this.events.onopen = (e) => console.log(e);
        //this.events.onmessage = (e) => console.log(e);
        this.events.onmessage = ({data}) => console.log(data);
    }



}

new Events();