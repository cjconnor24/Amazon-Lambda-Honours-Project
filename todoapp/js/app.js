(function (window, document) {

    const todoListDOM = document.getElementById('todoList');
    const todoInputDOM = document.getElementById('todoInput');
    const countDOM = document.querySelector('.count');
    let todoList = [];
    // let todoList = [{
    //     id: "5648",
    //     text: "Todo list one"
    // },
    // {
    //     id: "12340",
    //     text: "Todo List Two"
    // }];

    const API_URL = "https://kjot90gbi9.execute-api.eu-west-2.amazonaws.com/py3-dev/todos";
    // const API_URL = "https://hw403l2xsk.execute-api.eu-west-2.amazonaws.com/dev/todos";

    // THIS INITIALLY TRIES TO GET THE TODOLIST
    fetch(API_URL)
        .then(res => res.json())
        .then(json => {
            console.log(json);
            todoList = todoList.concat(json);

            render(todoList);
        })
        .catch(err => {
            console.log(err);
        });

    renderTodoList(todoList);

    // RENDER THE OUTPUT
    function renderTodoList(todoList) {
        const html = todoList.map((item, index) => `<li class="list">
                <a class="${item.checked ? 'finish' : 'unfinished'}" data-id=${item.id}></a>
                <p class="desc" data-id=${item.id}>
                    ${item.text}
                </p>
                <a class="del" data-id=${item.id}>X</a>
            </li>`).join('')
        todoListDOM.innerHTML = html;
        countDOM.innerHTML = todoList.length;
    }

    // CREATE ITEM
    const addItem = item => {
        fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })
            .then(res => res.json())
            .then(json => {
                console.log(json);
                todoList.push(json);
                render(todoList);
            })
            .catch(err => {
                console.log(err);

                // TODO: REMOVE THIS
                // todoList.push(item);
                // render(todoList);

            })
    }

    const newItem = value => ({ text: value, checked: false });



    // Modify to-do items (PUT)
    const toggleItem = id => {
        const currentSelectItem = todoList.find(item => item.id === id);

        // REVERSE THE CHECKED PROPERTY 
        currentSelectItem.checked = !currentSelectItem.checked;

        fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(currentSelectItem)
        })
            .then(res => res.json())
            .then(json => {
                render(todoList);
            })
            .catch(err => {

                // TODO: REMOVE THIS
                // render(todoList);

            })
    }

    // DELETE TO DO ITEMS (DELETE)
    const removeItem = id => {

        console.log(`Will attempt to remove ${id} from the list`);

        var t1 = new Date().getTime();
        var t2;

        fetch(`${API_URL}/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                t2 = new Date().getTime();
                console.log(`Response time: ${t2 - t1}ms`)
                res.json()
            })
            .then(json => {
                todoList = todoList.filter(item => item.id !== id);
                render(todoList);
            })
            .catch(err => {

                // TODO: REMOVE THIS
                // todoList = todoList.filter(item => item.id !== id);
                // render(todoList);

            })
    }


    // EVENT LISTENERS ------

    todoInputDOM.addEventListener('keydown', event => {
        if (event.keyCode === 13 && event.target.value) {

            // CREATE THE ITEM
            addItem(newItem(event.target.value));

            // CLEAR THE INPUT
            event.target.value = '';
        } else {
            console.log('probably blank...');
        }
    });

    todoListDOM.addEventListener('click', event => {

        const currentTarget = event.target;
        console.log(currentTarget);

        // CHECK WHAT WAS CLICKED AND MAKE SURE IT WAS THE TOGGLE BUTTON OR THE DESCRIPTIONG
        if (currentTarget && (currentTarget.matches('a.unfinished') || currentTarget.matches('a.finish') || currentTarget.matches('.desc'))) {
            toggleItem(currentTarget.dataset.id)
        } else if (currentTarget && currentTarget.matches('a.del')) {

            // DELETE ITEM
            removeItem(currentTarget.dataset.id)
        }
    });

    function render(todoList) {
        renderTodoList(todoList);
    }

}(window, document));