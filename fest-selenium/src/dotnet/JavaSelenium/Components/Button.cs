﻿// Date: Oct 15 2009
// Mel Llaguno
// http://www.aclaro.com
// -----------------------------------------
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

using System;
using JavaSelenium.Components.Interfaces;

namespace JavaSelenium.Components
{
    public class Button : Component, IHasInputValue<Button>, IHasCoordinates<Button>
    {
        private const string TYPE_STRING = "button";
        private string _value;
        private Coordinates _coordinates;

        public Button(string pName) : base(pName, TYPE_STRING)
        {}

        public Button(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasInputValue<Button>

        public string Value()
        {
            if (_value == null)
                _value = String.Empty;
            return _value;
        }

        public Button Value(string pValue)
        {
            _value = pValue;
            return this;
        }

        #endregion

        #region Implementation of IHasCoordinates<Button>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public Button Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
